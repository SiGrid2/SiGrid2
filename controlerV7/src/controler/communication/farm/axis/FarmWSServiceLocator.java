/**
 * FarmWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package controler.communication.farm.axis;

public class FarmWSServiceLocator extends org.apache.axis.client.Service implements controler.communication.farm.axis.FarmWSService {

	public FarmWSServiceLocator() {
	}


	public FarmWSServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public FarmWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for FarmWS
	private java.lang.String FarmWS_address = "http://localhost:8080/controllerv7/services/FarmWS";

	public java.lang.String getFarmWSAddress() {
		return FarmWS_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String FarmWSWSDDServiceName = "FarmWS";

	public java.lang.String getFarmWSWSDDServiceName() {
		return FarmWSWSDDServiceName;
	}

	public void setFarmWSWSDDServiceName(java.lang.String name) {
		FarmWSWSDDServiceName = name;
	}

	public controler.communication.farm.axis.FarmWS getFarmWS() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(FarmWS_address);
		}
		catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getFarmWS(endpoint);
	}

	public controler.communication.farm.axis.FarmWS getFarmWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			controler.communication.farm.axis.FarmWSSoapBindingStub _stub = new controler.communication.farm.axis.FarmWSSoapBindingStub(portAddress, this);
			_stub.setPortName(getFarmWSWSDDServiceName());
			return _stub;
		}
		catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setFarmWSEndpointAddress(java.lang.String address) {
		FarmWS_address = address;
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (controler.communication.farm.axis.FarmWS.class.isAssignableFrom(serviceEndpointInterface)) {
				controler.communication.farm.axis.FarmWSSoapBindingStub _stub = new controler.communication.farm.axis.FarmWSSoapBindingStub(new java.net.URL(FarmWS_address), this);
				_stub.setPortName(getFarmWSWSDDServiceName());
				return _stub;
			}
		}
		catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("FarmWS".equals(inputPortName)) {
			return getFarmWS();
		}
		else  {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://axis.farm.communication.controler", "FarmWSService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "FarmWS"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("FarmWS".equals(portName)) {
			setFarmWSEndpointAddress(address);
		}
		else 
		{ // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
