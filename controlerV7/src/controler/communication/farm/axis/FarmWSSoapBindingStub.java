/**
 * FarmWSSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package controler.communication.farm.axis;

public class FarmWSSoapBindingStub extends org.apache.axis.client.Stub implements controler.communication.farm.axis.FarmWS {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc [] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[13];
		_initOperationDesc1();
		_initOperationDesc2();
	}

	private static void _initOperationDesc1(){
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("register");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "initID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		oper.setReturnClass(int.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "registerReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("unregister");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "initID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getActionFromControler");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		oper.setReturnClass(int.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "getActionFromControlerReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("getTestRun");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://farm.transport.simobjects", "TestrunAxis"));
		oper.setReturnClass(simobjects.transport.farm.TestrunAxis.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "getTestRunReturn"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("loadedSim");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("startedSim");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[5] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("abortedSim");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("finishedSim");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[7] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("addSimResult");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "simResult"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimResultAxis"), simobjects.transport.farm.SimResultAxis.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[8] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("addSimLogMessage");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "message"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[9] = oper;

	}

	private static void _initOperationDesc2(){
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("addSimStatistics");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "stats"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimStatisticsAxis"), simobjects.transport.farm.SimStatisticsAxis.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[10] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("setSimState");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "trID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "simState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimStateAxis"), simobjects.transport.farm.SimStateAxis.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[11] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("setReceiverState");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "receiverState"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://farm.transport.simobjects", "ReceiverStateAxis"), simobjects.transport.farm.ReceiverStateAxis.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[12] = oper;

	}

	public FarmWSSoapBindingStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public FarmWSSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public FarmWSSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName("http://axis.farm.communication.controler", "ArrayOf_tns1_JobAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.JobAxis[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "JobAxis");
		qName2 = new javax.xml.namespace.QName("http://axis.farm.communication.controler", "item");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://axis.farm.communication.controler", "ArrayOf_tns1_ServerAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.ServerAxis[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "ServerAxis");
		qName2 = new javax.xml.namespace.QName("http://axis.farm.communication.controler", "item");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://axis.farm.communication.controler", "ArrayOf_xsd_long");
		cachedSerQNames.add(qName);
		cls = long[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
		qName2 = new javax.xml.namespace.QName("http://axis.farm.communication.controler", "item");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "AlgorithmAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.AlgorithmAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "JobAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.JobAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "LayoutAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.LayoutAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "MatrixAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.MatrixAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "ReceiverStateAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.ReceiverStateAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "ServerAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.ServerAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimResultAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.SimResultAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimStateAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.SimStateAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "SimStatisticsAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.SimStatisticsAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://farm.transport.simobjects", "TestrunAxis");
		cachedSerQNames.add(qName);
		cls = simobjects.transport.farm.TestrunAxis.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
						javax.xml.namespace.QName qName =
							(javax.xml.namespace.QName) cachedSerQNames.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class)
							cachedSerFactories.get(i);
							java.lang.Class df = (java.lang.Class)
							cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
						else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
							cachedSerFactories.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
							cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		}
		catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public int register(long initID, java.lang.String name) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "register"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Long(initID), name});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return ((java.lang.Integer) _resp).intValue();
			} catch (java.lang.Exception _exception) {
				return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void unregister(int receiverID, long initID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "unregister"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Long(initID)});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public int getActionFromControler(int receiverID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "getActionFromControler"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID)});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return ((java.lang.Integer) _resp).intValue();
			} catch (java.lang.Exception _exception) {
				return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public simobjects.transport.farm.TestrunAxis getTestRun(int receiverID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "getTestRun"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID)});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		else {
			extractAttachments(_call);
			try {
				return (simobjects.transport.farm.TestrunAxis) _resp;
			} catch (java.lang.Exception _exception) {
				return (simobjects.transport.farm.TestrunAxis) org.apache.axis.utils.JavaUtils.convert(_resp, simobjects.transport.farm.TestrunAxis.class);
			}
		}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void loadedSim(int receiverID, int trID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "loadedSim"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID)});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void startedSim(int receiverID, int trID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "startedSim"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID)});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void abortedSim(int receiverID, int trID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "abortedSim"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID)});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void finishedSim(int receiverID, int trID) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "finishedSim"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID)});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void addSimResult(int receiverID, int trID, simobjects.transport.farm.SimResultAxis simResult) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "addSimResult"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID), simResult});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void addSimLogMessage(int receiverID, int trID, java.lang.String message) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "addSimLogMessage"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID), message});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void addSimStatistics(int receiverID, int trID, simobjects.transport.farm.SimStatisticsAxis stats) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "addSimStatistics"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID), stats});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void setSimState(int receiverID, int trID, simobjects.transport.farm.SimStateAxis simState) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[11]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "setSimState"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), new java.lang.Integer(trID), simState});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public void setReceiverState(int receiverID, simobjects.transport.farm.ReceiverStateAxis receiverState) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[12]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("http://axis.farm.communication.controler", "setReceiverState"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(receiverID), receiverState});

		if (_resp instanceof java.rmi.RemoteException) {
			throw (java.rmi.RemoteException)_resp;
		}
		extractAttachments(_call);
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

}
