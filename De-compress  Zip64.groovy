
import java.util.Properties;

import java.io.InputStream;

import java.util.zip.ZipEntry;

import java.util.zip.ZipException;

import java.util.zip.ZipInputStream;

import com.boomi.execution.ExecutionUtil; 

 

for( int i = 0; i < dataContext.getDataCount(); i++ ) {

   

  InputStream is = dataContext.getStream(i);

  Properties props = dataContext.getProperties(i);

  ZipInputStream zipin = new ZipInputStream(is);

  ZipEntry ze;

   

  while ((ze = zipin.getNextEntry()) != null) {     

    String filename = ze.getName();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    int b = zipin.read();     

    while (b >= 0) {

      baos.write(b);

      b = zipin.read();

    }

     

    props.setProperty("document.dynamic.userdefined.DDP_Filename", filename);     

    dataContext.storeStream(new ByteArrayInputStream(baos.toByteArray()), props);

  }

   

  zipin.close(); 

}