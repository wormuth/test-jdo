package mydomain.model;

import java.util.HashMap;
import java.util.Map;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Person {
    @PrimaryKey
    private Long id;

    @Persistent
    @Join(column = "TAG_ID")
    private Map<Identity,Tag> tags;

    @Persistent
    @Join(column = "REMARK_ID")
    private Map<Identity,String> remarks;

    public Person( long id ) {
        this.id = id;
        this.tags = new HashMap<>();
        this.remarks = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void addTag( final Identity id, final Tag tag ) {
        this.tags.put( id, tag );
    }

    public void addRemark( final Identity id, final String remark ) {
        this.remarks.put( id, remark );
    }

    public Map<Identity,Tag> getTags() {
        return this.tags;
    }

    public Map<Identity,String> getRemarks() {
        return this.remarks;
    }

}
