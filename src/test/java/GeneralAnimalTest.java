import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class GeneralAnimalTest{
  GeneralAnimal testGeneralAnimal;
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Before
  public void setUp(){
    testRegularAnimal = new GeneralAnimal("sloth", "sloth");
  }

  @Test
  public void animals_instantiatesCorrectly_true() {
    assertTrue(testGeneralAnimal instanceof GeneralAnimal);
  }

  @Test
  public void name_instantiatesCorrectly_true() {
    assertEquals("sloth", testGeneralAnimal.getName());
  }

  @Test
  public void species_instantiatesCorrectly_true() {
    assertEquals("sloth", testGeneralAnimal.getSpecies());
  }

  @Test
  public void equals_returnsTrueIfPropertiesAreSame_true(){
    GeneralAnimal testGeneralAnimal2 = new GeneralAnimal("sloth", "sloth");
    assertTrue(testGeneralAnimal.equals(testGeneralAnimal2));
  }

  @Test
  public void save_insertsGeneralAnimalIntoDatabase_GeneralAnimal() {
    testGeneralAnimal.save();
    GeneralAnimal testGeneralAnimal2 = null;
    try(Connection con = DB.sql2o.open()){
      testRegularAnimal2 = con.createQuery("SELECT * FROM animals WHERE name='sloth'").throwOnMappingFailure(false)
      .executeAndFetchFirst(GeneralAnimal.class);
    }
    assertTrue(testGeneralAnimal2.equals(testGeneralAnimal));
  }

  @Test
  public void all_returnsAllInstancesOfPerson_true() {
    testGeneralAnimal.save();
    GeneralAnimal testGeneralAnimal2 = new GeneralAnimal("bitey", "squirrel");
    testGeneralAnimal2.save();
    assertEquals(true, GeneralAnimal.all().get(0).equals(testGeneralAnimal));
    assertEquals(true, GeneralAnimal.all().get(1).equals(testGeneralAnimal2));
  }

  @Test
  public void save_assignsIdToRegularAnimal() {
    testRegularAnimal.save();
    RegularAnimal testRegularAnimal2 = RegularAnimal.all().get(0);
    assertEquals(testRegularAnimal.getId(), testRegularAnimal2.getId());
  }

  @Test
  public void find_returnsRegularAnimalWithSameId_secondRegularAnimal() {
    testRegularAnimal.save();
    RegularAnimal testRegularAnimal2 = new RegularAnimal("bitey", "squirrel");
    testRegularAnimal2.save();
    assertEquals(RegularAnimal.find(testRegularAnimal2.getId()), testRegularAnimal2);
  }

  @Test(expected=IndexOutOfBoundsException.class)
  public void find_throwsExceptionIfAnimalNotFound() {
    RegularAnimal.find(1);
  }

  @Test
  public void findbyName_returnsRegularAnimalWithSameName_secondRegularAnimal() {
    testRegularAnimal.save();
    RegularAnimal testRegularAnimal2 = new RegularAnimal("bitey", "squirrel");
    testRegularAnimal2.save();
    assertEquals(RegularAnimal.findByName(testRegularAnimal2.getName()), testRegularAnimal2);
  }

  @Test
  public void delete_deletesEntryInDatabase_0(){
    testRegularAnimal.save();
    testRegularAnimal.delete();
    assertEquals(0, RegularAnimal.all().size());
  }

  @Test
  public void delete_deletesSightingAssociations(){
    testRegularAnimal.save();
    Sighting sighting = new Sighting("here", "Steve");
    sighting.save();
    sighting.addAnimal(testRegularAnimal);
    testRegularAnimal.delete();
    assertEquals(0, sighting.getRegularAnimals().size());
  }

  @Test
  public void getSightings_returnsAllSightings_int(){
    testRegularAnimal.save();
    Sighting testSighting = new Sighting("Here", "Steve");
    testSighting.save();
    testSighting.addAnimal(testRegularAnimal);
    List savedSightings = testRegularAnimal.getSightings();
    assertEquals(1, savedSightings.size());
    assertTrue(savedSightings.contains(testSighting));
  }
}
