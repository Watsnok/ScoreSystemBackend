package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();



    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName(), person.getScore()));
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        for( Person p : DB)
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        return null;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isPresent()){
            DB.remove(personMaybe.get());
            return 1;
        }

        return 0;
    }

    @Override
    public int updatePersonByID(UUID id, Person update) {

        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if(indexOfPersonToUpdate >= 0){
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName(), update.getScore()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);

    }

    @Override
    public int sortList() {
        //DB.sort(Comparator.comparing(Person::getScore));

        Collections.sort(DB, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                //return o1.getScore().compareTo(o2.getScore());
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });


        return 1;
    }


}
