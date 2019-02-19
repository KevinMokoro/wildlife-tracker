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
    testGeneralAnimal = new GeneralAnimal("sloth", "sloth");
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
      testGeneralAnimal2 = con.createQuery("SELECT * FROM animals WHERE name='sloth'").throwOnMappingFailure(false)
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
  public void save_assignsIdToGeneralAnimal() {
    testGeneralAnimal.save();
    GeneralAnimal testGeneralAnimal2 = GeneralAnimal.all().get(0);
    assertEquals(testGeneralAnimal.getId(), testGeneralAnimal2.getId());
  }

  @Test
  public void find_returnsGeneralAnimalWithSameId_secondGeneralAnimal() {
    testGeneralAnimal.save();
    GeneralAnimal testGeneralAnimal2 = new GeneralAnimal("bitey", "squirrel");
    testGeneralAnimal2.save();
    assertEquals(GeneralAnimal.find(testGeneralAnimal2.getId()), testGeneralAnimal2);
  }

  @Test(expected=IndexOutOfBoundsException.class)
  public void find_throwsExceptionIfAnimalNotFound() {
    GeneralAnimal.find(1);
  }

  @Test
  public void findbyName_returnsGeneralAnimalWithSameName_secondGeneralAnimal() {
    testGeneralAnimal.save();
    GeneralAnimal testGeneralAnimal2 = new GeneralAnimal("bitey", "squirrel");
    testGeneralAnimal2.save();
    assertEquals(GeneralAnimal.findByName(testGeneralAnimal2.getName()), testGeneralAnimal2);
  }

  @Test
  public void delete_deletesEntryInDatabase_0(){
    testGeneralAnimal.save();
    testGeneralAnimal.delete();
    assertEquals(0, GeneralAnimal.all().size());
  }

  @Test
  public void delete_deletesSightingAssociations(){
    testGeneralAnimal.save();
    Sighting sighting = new Sighting("here", "Steve");
    sighting.save();
    sighting.addAnimal(testGeneralAnimal);
    testGeneralAnimal.delete();
    assertEquals(0, sighting.getGeneralAnimals().size());
  }

  @Test
  public void getSightings_returnsAllSightings_int(){
    testGeneralAnimal.save();
    Sighting testSighting = new Sighting("Here", "Steve");
    testSighting.save();
    testSighting.addAnimal(testGeneralAnimal);
    List savedSightings = testGeneralAnimal.getSightings();
    assertEquals(1, savedSightings.size());
    assertTrue(savedSightings.contains(testSighting));
  }
}
