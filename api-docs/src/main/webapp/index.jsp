<%@ page language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%!
private static final String DEFAULT_SWAGGER_API_URI = "/site/restapi/swagger.yaml";
private static final String SWAGGER_POM_PROPS_RESOURCE_PATH = "META-INF/maven/org.webjars/swagger-ui/pom.properties";

private volatile String swaggerVersion;

private String getSwaggerModuleVersion() throws IOException {
    String version = swaggerVersion;
    if (version == null) {
        synchronized (this) {
            version = swaggerVersion;
            if (version == null) {
                InputStream is = null;
                BufferedInputStream bis = null;
                try {
                    is = Thread.currentThread().getContextClassLoader().getResourceAsStream(SWAGGER_POM_PROPS_RESOURCE_PATH);
                    bis = new BufferedInputStream(is);
                    Properties props = new Properties();
                    props.load(bis);
                    swaggerVersion = version = props.getProperty("version");
                    props.load(bis);
                } finally {
                    if (bis != null) { bis.close(); }
                    if (is != null) { is.close(); }
                }
            }
        }
    }
    return version;
}
%>

<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1);

String swaggerUrl = request.getParameter("url");
if (swaggerUrl == null) {
    swaggerUrl = DEFAULT_SWAGGER_API_URI;
}
final String redirectPath = "/webjars/swagger-ui/" + getSwaggerModuleVersion() + "/index.html?url=" + swaggerUrl;
response.sendRedirect(request.getContextPath() + redirectPath);
%>
