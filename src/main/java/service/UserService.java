package service;

import domain.User;
import dto.user.UserResponse;
import exception.DataAccessException;
import exception.IncompleteUserException;
import exception.UserNotFoundException;
import repository.dao.FileUserDao;
import repository.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
  private final UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public UserResponse getByUsername(String username)
      throws UserNotFoundException, DataAccessException {
    User user =
        userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    return new UserResponse(user.getId(), user.getUsername());
  }

  // Ejercicio 6
  public User getValidatedUser(String username, UserDao userDao)
      throws UserNotFoundException, IncompleteUserException, DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 6: implement getValidatedUser");
  }

  // Ejercicio 9
  public List<User> getUsersPresentInFileAndDao(FileUserDao fileUserDao, UserDao userDao) throws DataAccessException {
    List<User> UserFile = fileUserDao.findAll();
    ArrayList<User> userList = new ArrayList<>(); 
    
    for (User user: UserFile){
      try{
        Optional<User> userFromDao = userDao.findByUsername(user.getUsername());
        if(userFromDao.isPresent()){
          userList.add(userFromDao.get());
        }
      }
      catch(DataAccessException e){}
    }

    return userList;
        //throw new UnsupportedOperationException("Ejercicio 9: implement getUsersPresentInFileAndDao");
  }
}
