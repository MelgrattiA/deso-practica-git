package repository.dao;

import domain.User;
import exception.DataAccessException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUserDaoImpl implements FileUserDao {
  private final File file;

  public FileUserDaoImpl() {
    this.file = new File("users.txt");
  }

  // visible for testing, so tests can point at a temp file instead of the real one
  FileUserDaoImpl(File file) {
    this.file = file;
  }

  // Ejercicio 9 (Parte A)
  @Override
  public List<User> findAll() throws DataAccessException {
    try (BufferedReader userText = new BufferedReader(new FileReader(file))){

      ArrayList<User> userList = new ArrayList<>();
      String line = userText.readLine();

      while (line != null){
        String[] words = line.split(" ");

        if(lineaValida(words)){
          userList.add(new User(UUID.fromString(words[0]), words[1], null, null));
        }

        line = userText.readLine();
      }

      return userList;
    }
    catch(FileNotFoundException e){
      throw new DataAccessException(null);
    }
    catch(IOException e){
      throw new DataAccessException(null);
    }
    //throw new UnsupportedOperationException("Ejercicio 9: implement findAll");
  }

  private Boolean lineaValida (String[] line){
    if(line.length==2){ 
      try {
        UUID.fromString(line[0]); //probamos si el primer argumento se puede transformar a UUID
        return true;

      } catch (IllegalArgumentException e) {
        return false;
      }

    }
    else{
      return false;
    }
  }

}
