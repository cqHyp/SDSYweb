package web.dao;

import java.util.List;

import web.model.Address;

public interface AddressDao {
	public void newAddress(Address address);
	public void deleteAddress(Address address);
	public double pageSum();
	public List<Address> allAddress(int i);
	public List<Address> allAddress();
	public Address findAddress(int addressId);
	public void updateAddress(Address address);
	

}
