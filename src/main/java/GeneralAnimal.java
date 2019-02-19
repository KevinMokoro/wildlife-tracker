import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class GeneralAnimal extends Animal {

  public GeneralAnimal(String name, String species){
    this.name = name;
    this.species = species;
    endangered = false;
  }

  public static List<GeneralAnimal> all() {
    String sql = "SELECT * FROM animals WHERE endangered = 'false'";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(GeneralAnimal.class);
    }
  }

  public static GeneralAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where id=:id";
      GeneralAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(GeneralAnimal.class);
      if(animal == null){
        throw new IndexOutOfBoundsException("I'm sorry, I think this animal does not exist");
      }
      return animal;
    }
  }

  public static GeneralAnimal findByName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where name=:name";
      GeneralAnimal animal = con.createQuery(sql)
        .addParameter("name", name)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(GeneralAnimal.class);
      return animal;
    }
  }
}
