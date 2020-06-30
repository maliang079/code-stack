package com.ml.demo.mybatis.generator.core.options;

/**
 * Created by mal on 2019/5/22.
 */
public class Context {

    private JdbcConnectionConfiguration jdbcConnectionConfiguration;

    private JavaModelConfiguration javaModelConfiguration;

    private JavaMapperConfiguration javaMapperConfiguration;

    private XmlMapperConfiguration xmlMapperConfiguration;

    public JdbcConnectionConfiguration getJdbcConnectionConfiguration() {
        return jdbcConnectionConfiguration;
    }

    public void setJdbcConnectionConfiguration(JdbcConnectionConfiguration jdbcConnectionConfiguration) {
        this.jdbcConnectionConfiguration = jdbcConnectionConfiguration;
    }

    public JavaModelConfiguration getJavaModelConfiguration() {
        return javaModelConfiguration;
    }

    public void setJavaModelConfiguration(JavaModelConfiguration javaModelConfiguration) {
        this.javaModelConfiguration = javaModelConfiguration;
    }

    public JavaMapperConfiguration getJavaMapperConfiguration() {
        return javaMapperConfiguration;
    }

    public void setJavaMapperConfiguration(JavaMapperConfiguration javaMapperConfiguration) {
        this.javaMapperConfiguration = javaMapperConfiguration;
    }

    public XmlMapperConfiguration getXmlMapperConfiguration() {
        return xmlMapperConfiguration;
    }

    public void setXmlMapperConfiguration(XmlMapperConfiguration xmlMapperConfiguration) {
        this.xmlMapperConfiguration = xmlMapperConfiguration;
    }

}
