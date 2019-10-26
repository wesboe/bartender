package nl.ing.jfall.bar.bartender.monitoring;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.actuate.metrics.web.servlet.DefaultWebMvcTagsProvider;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;
import org.springframework.stereotype.Component;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link WebMvcTagsProvider}. It adds an additional tag with the common name of the certificate
 */
@Component
public class CertificateWebMvcTagsProvider implements WebMvcTagsProvider {

    @Override
    public Iterable<Tag> getTags(HttpServletRequest request, HttpServletResponse response,
                                 Object handler, Throwable exception) {
		List<Tag> tags = new ArrayList<>();
		new DefaultWebMvcTagsProvider().getTags(request, response, handler, exception).forEach(tags::add);

        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
        if (certs != null) {
            tags.add(Tag.of("client_name", retrieveCommonName(certs[0])));
        }

		return Tags.of(tags);

    }

	private String retrieveCommonName(X509Certificate cert) {
		try {
			String dn = cert.getSubjectX500Principal().getName();
			LdapName ldapDN = new LdapName(dn);
			return ldapDN.getRdns().stream()
					.filter(rdn -> rdn.getType().equalsIgnoreCase("CN"))
					.map(rdn -> ""+rdn.getValue())
					.findFirst()
					.orElse("");

		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
    public Iterable<Tag> getLongRequestTags(HttpServletRequest request, Object handler) {
        return Tags.of(WebMvcTags.method(request), WebMvcTags.uri(request, null));
    }

}
