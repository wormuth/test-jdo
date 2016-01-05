package mydomain.model;

import org.datanucleus.store.types.converters.TypeConverter;

public class IdentityMapping implements TypeConverter<Identity,String> {

    public IdentityMapping() {
        super();
    }

    @Override
    public String toDatastoreType( final Identity memberValue ) {
        return memberValue != null ? memberValue.toString() : null;
    }

    @Override
    public Identity toMemberType( final String datastoreValue ) {
        return datastoreValue != null ? Identity.getInstance( datastoreValue ) : null;
    }

    private static final long serialVersionUID = 3979708818330320124L;
}