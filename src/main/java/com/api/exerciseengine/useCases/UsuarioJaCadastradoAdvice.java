// package com.api.exerciseengine.useCases;

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.ResponseStatus;

// @ControllerAdvice
// public class UsuarioJaCadastradoAdvice {

//   @ResponseBody
//   @ExceptionHandler(UsuarioJaCadastradoException.class)
//   @ResponseStatus(HttpStatus.NOT_FOUND)
//   String alunoNotFoundHandler(UsuarioJaCadastradoException ex) {
//     final String message = ex.getMessage();
//     return message;
//   }
// }
