package DB;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/main/resources/db.properties"
})
public interface Props extends Config {

    @Key("db.url")
    String url();

    @Key("db.user")
    String user();

    @Key("db.password")
    String password();
}
