package codeandtell.camel.jboss.spring;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import codeandtell.camel.jboss.JbossResolverUtil;
import codeandtell.camel.jboss.JbossTypeConverter;

import org.apache.camel.util.ResolverUtil;
import org.apache.camel.spring.SpringCamelContext;

@XmlRootElement(name = "camelContext")
@XmlAccessorType(XmlAccessType.FIELD)
public class CamelContextFactoryBean extends org.apache.camel.spring.CamelContextFactoryBean {

    /**
     * The factory method to create the ResolverUtil
     * @return a new instance of ResolverUtil
     */
    @Override
    protected ResolverUtil createResolverUtil() {
        // return vfs aware resolver
        return new JbossResolverUtil();
    }

    @Override
    protected SpringCamelContext createContext() {
        SpringCamelContext ctx = super.createContext();
        ctx.setTypeConverter(new JbossTypeConverter(ctx.getInjector()));
        return ctx;
    }
}
