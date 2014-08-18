package com.github.helloiampau;

import com.github.helloiampau.model.MyModel;
import com.github.helloiampau.model.exception.InvalidJsonReceivedException;
import com.github.helloiampau.model.exception.NoModelFoundException;
import com.google.gson.JsonIOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * gson-example
 * Created by Pasquale Boemio <boemianrapsodi@gmail.com>
 * <p/>
 * 18 August 2014.
 */
public class ResourceKeeper extends HttpServlet {

  MyModel model;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");

    try {
      PrintWriter writer = response.getWriter();

      if(model == null)
        throw new NoModelFoundException();

      System.out.println("Sending last stored model");

      response.setStatus(HttpServletResponse.SC_OK);
      writer.println(this.model.toJson());
    } catch (NoModelFoundException e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    } catch (IOException e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("application/json");

    try {
      PrintWriter writer = response.getWriter();

      String bodyContent = this.readBody(request.getReader());
      if (bodyContent.equals(""))
        throw new InvalidJsonReceivedException();

      this.model = MyModel.fromJson(bodyContent);

      System.out.println("JsonObject parsed:");
      System.out.println(this.model.getaStringValue());
      System.out.println(this.model.getAnIntegerValue());

      response.setStatus(HttpServletResponse.SC_OK);
      writer.println(this.model.toJson());
    } catch (InvalidJsonReceivedException e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    } catch (IOException e) {
      e.printStackTrace();
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }


  }

  private String readBody(BufferedReader reader) throws IOException {
    StringBuilder builder = new StringBuilder();
    String aux;

    while ((aux = reader.readLine()) != null)
      builder.append(aux);

    return builder.toString();

  }

}
