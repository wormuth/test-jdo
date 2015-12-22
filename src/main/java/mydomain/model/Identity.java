package mydomain.model;

public class Identity implements Comparable<Identity> {

    public static Identity getInstance( final String value ) {
        return new Identity( value );
    }

    protected Identity( final String value ) {
        super();
        this.value = value;
    }

    @Override
    public int compareTo( final Identity other ) {
        return other != null ? toString().compareTo( other.toString() ) : 1;
    }

    public @Override boolean equals( final Object other ) {
        if ( other == this ) {
            return true;
        }
        if ( !( other instanceof Identity ) ) {
            return false;
        }
        return this.value.equals( ( ( Identity ) other ).value );
    }

    public @Override int hashCode() {
        return this.value.hashCode();
    }

    public @Override String toString() {
        return this.value;
    }

    private String value;
}
