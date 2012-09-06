package fi.ixonos.projects.logic;

import org.junit.Test;

/**
 *
 * @author katrami
 */
public interface TestInterface {

    @Test
    public void testInsert();

    @Test
    public void testUpdate();

    @Test
    public void testLoad();

    @Test
    public void testDelete();
}
