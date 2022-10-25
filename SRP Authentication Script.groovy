
import java.util.Properties; 
import java.io.InputStream;

import aws.cognito.authorization.CognitoAuthenticationHelper;

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    
    String poolId =  props.getProperty("document.dynamic.userdefined.DDP_POOL_ID");
    String clientAppId = props.getProperty("document.dynamic.userdefined.DDP_CLIENT_APP_ID");
    String clientAppSecretKey = props.getProperty("document.dynamic.userdefined.DDP_CLIENT_APP_SECRET");
    String region = props.getProperty("document.dynamic.userdefined.DDP_REGION");
    String username = props.getProperty("document.dynamic.userdefined.DDP_USERNAME");
    String password = props.getProperty("document.dynamic.userdefined.DDP_PASSWORD");

    CognitoAuthenticationHelper helper = new CognitoAuthenticationHelper(poolId, clientAppId, clientAppSecretKey != null ? clientAppSecretKey : "", region);
    String idToken = helper.PerformSRPAuthentication(username, password);
    props.setProperty("document.dynamic.userdefined.DDP_SRP_TOKEN", idToken);


    dataContext.storeStream(is, props);
}
