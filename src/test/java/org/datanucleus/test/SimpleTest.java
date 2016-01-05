package org.datanucleus.test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.datanucleus.util.NucleusLogger;
import org.junit.Test;

import mydomain.model.Identity;
import mydomain.model.Person;
import mydomain.model.Tag;

public class SimpleTest {
    @Test
    public void testSimple() {
        NucleusLogger.GENERAL.info( ">> test START" );
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory( "MyTest" );

        // create test object:
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();

            final Person person = new Person( 0 );
            person.addTag( Identity.getInstance( "id of tag" ), Tag.getInstance( "content of tag" ) );
            person.addRemark( Identity.getInstance( "id of remark" ), "content of remark" );

            pm.makePersistent( person );
            tx.commit();
        }
        catch ( Throwable thr ) {
            NucleusLogger.GENERAL.error( ">> Exception in test", thr );
            fail( "Failed test : " + thr.getMessage() );
        }
        finally {
            if ( tx.isActive() ) {
                tx.rollback();
            }
            pm.close();
        }

        // query for test object:
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try {
            tx.begin();

            final Query<Person> queryForRemarks = pm.newQuery( Person.class, "this.remarks.containsKey( 'id of remark' ) && this.remarks.get( 'id of remark' ).indexOf( 'remark' ) > -1" );
            List<Person> persons = queryForRemarks.executeList();
            assertTrue( !persons.isEmpty() );
            
            final Query<Person> queryForTags = pm.newQuery( Person.class, "this.tags.containsKey( 'id of tag' ) && this.tags.get( 'id of tag' ).indexOfTag ( 'tag' ) > -1" );
            persons = queryForTags.executeList();
            assertTrue( !persons.isEmpty() );

            tx.commit();
        }
        catch ( Throwable thr ) {
            NucleusLogger.GENERAL.error( ">> Exception in test", thr );
            fail( "Failed test : " + thr.getMessage() );
        }
        finally {
            if ( tx.isActive() ) {
                tx.rollback();
            }
            pm.close();
        }

        pmf.close();
        NucleusLogger.GENERAL.info( ">> test END" );
    }
}
