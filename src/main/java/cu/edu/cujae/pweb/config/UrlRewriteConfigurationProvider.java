package cu.edu.cujae.pweb.config;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

@RewriteConfiguration
public class UrlRewriteConfigurationProvider extends HttpConfigurationProvider{
    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()

                .addRule(Join.path("/security-users").to("/pages/security/users/user-list.jsf"))
                .addRule(Join.path("/welcome").to("/pages/welcome/welcome.jsf"))
                .addRule(Join.path("/authors").to("/pages/authors/author-list.jsf"))
                .addRule(Join.path("/books").to("/pages/books/book-list.jsf"))
                .addRule(Join.path("/clients").to("/pages/client/client-list.jsf"))
                .addRule(Join.path("/copies").to("/pages/copies/copy-list.jsf"))
                .addRule(Join.path("/loans").to("/pages/loans/loan-list.jsf"))
                .addRule(Join.path("/loans-requests").to("/pages/loansRequest/loanRequest-list.jsf"))
                .addRule(Join.path("/matters").to("/pages/matters/matter-list.jsf"))
                .addRule(Join.path("/breaches").to("/pages/breaches/breach-list.jsf"))
                ;




        //ej using params
        //.addRule(Join.path("/shop/{category}").to("/faces/viewCategory.jsf"));
    }

    @Override
    public int priority() {
        return 0;
    }
}
