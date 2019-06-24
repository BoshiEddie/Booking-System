package BoshiPan;

public class Passenger {
	
	private String name;
	private String id;
	private String email;
	private String phone;
	private GPSLocation location;
	
	public Passenger() {};
	
	public Passenger(String name, String id, String email, String phone, GPSLocation location) {
//		error exception
		if(name == null||id==null) {
			throw new IllegalArgumentException("null arguments encountered");
		}
		
		this.name = name;
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public GPSLocation getLocation() {
		return location;
	}

	public void setLocation(GPSLocation location) {
		this.location = location;
	}

	
	@Override
	public String toString() {
		return this.getClass() +"[name="+ name + ", id=" + id + ", email=" + email + ", phone=" + phone + ", location="
				+ location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	
	public Passenger(Passenger passenger) {
		this.name = passenger.name;
		this.id= passenger.id;
		this.email = passenger.email;
		this.phone = passenger.phone;
		this.location = new GPSLocation(passenger.getLocation());
	}
	
}
