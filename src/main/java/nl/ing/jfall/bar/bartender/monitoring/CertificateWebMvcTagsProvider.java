package nl.ing.jfall.bar.bartender.monitoring;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.web.servlet.DefaultWebMvcTagsProvider;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;
import org.springframework.stereotype.Component;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.cert.X509Certificate;

/**
 * Implementation of {@link WebMvcTagsProvider}. It adds an additional tag with the common name of the certificate
 */
@Component
@Slf4j
public class CertificateWebMvcTagsProvider extends DefaultWebMvcTagsProvider {

    @Override
    public Iterable<Tag> getTags(HttpServletRequest request, HttpServletResponse response,
                                 Object handler, Throwable exception) {

        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");

		return Tags.of(super.getTags(request, response, handler, exception))
				.and(Tag.of("client_name", retrieveCommonName(certs)));

    }

	private String retrieveCommonName(X509Certificate[] cert) {
		try {
			if (cert.length == 0) {
				return "";
			}
			String dn = cert[0].getSubjectX500Principal().getName();
			LdapName ldapDN = new LdapName(dn);
			return ldapDN.getRdns().stream()
					.filter(rdn -> rdn.getType().equalsIgnoreCase("CN"))
					.map(rdn -> String.valueOf(rdn.getValue()))
					.findFirst()
					.orElse("");

		} catch (InvalidNameException e) {
			log.error(e.getMessage());
		}
		return "";
	}

	@Override
    public Iterable<Tag> getLongRequestTags(HttpServletRequest request, Object handler) {
        return Tags.of(WebMvcTags.method(request), WebMvcTags.uri(request, null));
    }

}
