
import java.util.Properties;
import java.io.InputStream;

import com.boomi.execution.ExecutionUtil;  // If use OPTION 3

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

// from https://community.boomi.com/s/article/howtovalidatexmlagainstanxmlschemausinggroovy

// Load a WXS schema, represented by a Schema instance
// BEGIN CHOOSE ONE //

// OPTION 1: URL
//URL schemaFile = new URL("http://www.someurl.com/schemas/books.xsd");

// OPTION 2: Local File
//Source schemaFile = new StreamSource(new File("C:/Test/In/books.xsd"));

// OPTION 3: Dynamic Process Property
String schemaDPP = ExecutionUtil.getDynamicProcessProperty("XSD_DATA");
Source schemaFile = new StreamSource(new StringReader(schemaDPP));

// END CHOOSE ONE //

for (int i = 0; i < dataContext.getDataCount(); i++) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

    // create a Source for the xml document to be validated
    Source xmlFile = new StreamSource(is);


    // Create a SchemaFactory capable of understanding WXS schemas
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema schema = factory.newSchema(schemaFile);

    // Create a Validator instance, which can be used to validate an instance document
    Validator validator = schema.newValidator();

    // Validate the xml document
    try {

        validator.validate(xmlFile);
        props.setProperty("document.dynamic.userdefined.isValid", "true");  

    } catch (SAXException e) {

        // instance document is invalid!
        props.setProperty("document.dynamic.userdefined.isValid", "false");  
        props.setProperty("document.dynamic.userdefined.validationError", e.getMessage());  

    }

    // Reset document InputStream to pass original xml document to next shape
    is.reset();
    dataContext.storeStream(is, props);
}
