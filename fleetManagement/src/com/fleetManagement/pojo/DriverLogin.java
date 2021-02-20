package com.fleetManagement.pojo;

public class DriverLogin {
	
	  	private String name;
	  	private String passworld;
	    
		public DriverLogin() {
			super();
			// TODO Auto-generated constructor stub
		}

		public DriverLogin(String name, String passworld) {
			super();
			this.name = name;
			this.passworld = passworld;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassworld() {
			return passworld;
		}

		public void setPassworld(String passworld) {
			this.passworld = passworld;
		}
	    
		 @Override
		    public String toString() {
		        return "{\r\n" +
		                "name='" + name + '\'' +
		                ", \r\n sex=" + passworld +
		                "\r\n}";
		    }
}
