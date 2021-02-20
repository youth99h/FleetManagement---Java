package com.fleetManagement.gui.extend;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import com.fleetManagement.pojo.anno.TableFiled;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;


public class ExtTable {

  private static ConcurrentHashMap<Class<?>, Object[]> colNamesMap = new ConcurrentHashMap<>();
  private static ConcurrentHashMap<Class<?>, List<TF>> tfsMap = new ConcurrentHashMap<>();
  private static ConcurrentHashMap<Object, Boolean> isApplyMap = new ConcurrentHashMap<>();

  public static JTabbedPane buildJTabbedPane(Map<String, Component> panelsMap) {
      // 选项卡面板
      JTabbedPane tabbedPane = new JTabbedPane();
      // 通过BorderFactory来设置边框的特性
      tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      panelsMap.forEach((s, jPanel) -> tabbedPane.add(s, jPanel));
      return tabbedPane;
  }

  public static JTable getDefaultTable(List<Integer> editCols) {
      JTable table = new JTable() {
          @Override
          public void updateUI() {
              // 刷新
              super.updateUI();
              // 表格行高
              setRowHeight(36);
          }

          @Override
          public boolean isCellEditable(int row, int column) {
              if (Objects.nonNull(editCols) && editCols.size() > 0 && editCols.contains(column)) {
                  return true;
              }
              return false;
          }
      };
      // 设置表头不可移动
      table.getTableHeader().setReorderingAllowed(false);
      // 一次只能选择一项
      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      return table;
  }

  public static DefaultTableModel buildTableModel(Object[] columnNames, Object[][] rowData) {
      return new DefaultTableModel(rowData, columnNames);
  }

  public static DefaultTableModel buildTableModel4Objects(List<?> objects, Class<?> clz) {
      Object[] cs = colNamesMap.get(clz);
      if (Objects.isNull(cs)) {
          Field[] dfs = clz.getDeclaredFields();
          List<String> colNames = new ArrayList<>();
          List<TF> tFs = new ArrayList<>();
          for (Field f : dfs) {
              TableFiled anno = f.getAnnotation(TableFiled.class);
              if (Objects.isNull(anno)) {
                  continue;
              }
              colNames.add(anno.name());
              f.setAccessible(true);
              TF tf = new TF(f, anno.index());
              tFs.add(tf);
          }
          colNamesMap.put(clz, colNames.toArray());
          tfsMap.put(clz, tFs);
      }

      if (Objects.isNull(objects) || objects.size() <= 0) {
          return new DefaultTableModel(new Object[][]{}, colNamesMap.get(clz));
      }

      Object[][] objs = new Object[objects.size()][colNamesMap.get(clz).length];
      int row = 0;
      for (Object o : objects) {
          for (TF f : tfsMap.get(clz)) {
              try {
                  objs[row][f.index] = f.getField().get(o);
              } catch (IllegalAccessException e) {
                  e.printStackTrace();
              }
          }
          row++;
      }

      return  new DefaultTableModel(objs, colNamesMap.get(clz));
  }

  static class TF{
      Field field;
      int index;

      public TF(Field field, int index) {
          this.field = field;
          this.index = index;
      }

      public Field getField() {
          return field;
      }

      public int getIndex() {
          return index;
      }
  }

  public static MouseInputListener buildMouseInputMenuListener(Map<JMenuItem, Function> metaMap) {
      return new MouseInputListener() {
          public void mouseClicked(MouseEvent e) {
              processEvent(e);
          }

          public void mousePressed(MouseEvent e) {
              processEvent(e);// is necessary!!!
          }

          public void mouseReleased(MouseEvent e) {

              if (e.getButton() == MouseEvent.BUTTON3) {// right click

                  JPopupMenu popupmenu = new JPopupMenu();

                  metaMap.forEach((jMenuItem, fun) -> {
                      popupmenu.add(jMenuItem);
                      Boolean ok = isApplyMap.get(jMenuItem);
                      if (Objects.isNull(ok) || !ok) {
                          jMenuItem.addActionListener(e1 -> {
                              fun.apply(0);
                          });
                          isApplyMap.put(jMenuItem, true);
                      }

                  });
                  popupmenu.show(e.getComponent(), e.getX(), e.getY());
              }
          }

          public void mouseEntered(MouseEvent e) {
              processEvent(e);
          }

          public void mouseExited(MouseEvent e) {
              processEvent(e);
          }

          public void mouseDragged(MouseEvent e) {
              processEvent(e);
          }

          public void mouseMoved(MouseEvent e) {
              processEvent(e);
          }

          private void processEvent(MouseEvent e) {
          }
      };
  }

}
