package mydomain.model;

import org.datanucleus.store.types.converters.TypeConverter;

public class TagMapping implements TypeConverter<Tag,String> {

    public TagMapping() {
        super();
    }

    @Override
    public String toDatastoreType( final Tag memberValue ) {
        return memberValue != null ? memberValue.toString() : null;
    }

    @Override
    public Tag toMemberType( final String datastoreValue ) {
        return datastoreValue != null ? Tag.getInstance( datastoreValue ) : null;
    }

    private static final long serialVersionUID = 6168434186202407352L;
}