package mydomain.model;

public class Tag implements Comparable<Tag> {

    public static Tag getInstance( final String value ) {
        return new Tag( value );
    }

    protected Tag( final String value ) {
        super();
        this.value = value;
    }

    @Override
    public int compareTo( final Tag other ) {
        return other != null ? toString().compareTo( other.toString() ) : 1;
    }

    public @Override boolean equals( final Object other ) {
        if ( other == this ) {
            return true;
        }
        if ( !( other instanceof Tag ) ) {
            return false;
        }
        return this.value.equals( ( ( Tag ) other ).value );
    }

    public @Override int hashCode() {
        return this.value.hashCode();
    }

    public @Override String toString() {
        return this.value;
    }

    private String value;
}
