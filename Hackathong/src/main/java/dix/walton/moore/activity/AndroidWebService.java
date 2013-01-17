package dix.walton.moore.activity;

import dix.walton.moore.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
  
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
  
public class AndroidWebService extends Activity {
   
    private final String NAMESPACE = "http://www.webserviceX.NET/";
    private final String URL = "http://www.webservicex.net/ConvertWeight.asmx";
    private final String SOAP_ACTION = "http://www.webserviceX.NET/ConvertWeight";
    private final String METHOD_NAME = "ConvertWeight";
  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
  
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
       
        String weight = "3700";
        String fromUnit = "Grams";
        String toUnit = "Kilograms";
       
        PropertyInfo weightProp =new PropertyInfo();
        weightProp.setName("Weight");
        weightProp.setValue(weight);
        weightProp.setType(double.class);
        request.addProperty(weightProp);
          
        PropertyInfo fromProp =new PropertyInfo();
        fromProp.setName("FromUnit");
        fromProp.setValue(fromUnit);
        fromProp.setType(String.class);
        request.addProperty(fromProp);
          
        PropertyInfo toProp =new PropertyInfo();
        toProp.setName("ToUnit");
        toProp.setValue(toUnit);
        toProp.setType(String.class);
        request.addProperty(toProp);
         
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
  
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
            Log.i("myApp", response.toString());
     
            TextView tv = new TextView(this);
            tv.setText(weight+" "+fromUnit+" equal "+response.toString()+ " "+toUnit);
            setContentView(tv);
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}