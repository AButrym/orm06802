package client;

import client.model.entity.Animal;
import client.model.entity.Zoo;
import orm.OrmManager;

public class MainClient {
    public static void main(String[] args) {
        var ormManager = new OrmManager("H2schema");
        var ormManagerPg = new OrmManager("PGschema");

        ormManager.registerEntities(Zoo.class, Animal.class);

        var zooOfNewYork = new Zoo("New York Zoo");
        System.out.println(zooOfNewYork.getId()); // null

        ormManager.persist(zooOfNewYork); // there is a row in DB table
        System.out.println(zooOfNewYork.getId()); // 1 (not null)

        long id = zooOfNewYork.getId();
        Zoo theZoo = ormManager.load(id, Zoo.class);

        zooOfNewYork.setName("Zoo of New York");
        ormManager.merge(zooOfNewYork);

        System.out.println(theZoo.getName().equals(
                zooOfNewYork.getName()
        )); // true if cache is used false if new object is loaded

        ormManager.update(theZoo);
        System.out.println(theZoo.getName().equals(
                zooOfNewYork.getName()
        )); // true

        ormManager.remove(theZoo); // send delete to DB and set id to null
        System.out.println(theZoo.getId()); // null


    }

    public static void testManyToOne() {
        var ormManager = new OrmManager("H2schema");
        ormManager.registerEntities(Zoo.class, Animal.class);

        var zoo = new Zoo("Kharkiv Zoo");
        var crocodile = new Animal("Gena");
        var strange = new Animal("Cheburashka");
        zoo.getAnimals().add(crocodile);
        assert crocodile.getZoo() == zoo; // orm has set it
//        crocodile.setZoo(zoo);
        zoo.getAnimals().add(strange);
        ormManager.persist(zoo); // change table zoo and table animal
    }
}
