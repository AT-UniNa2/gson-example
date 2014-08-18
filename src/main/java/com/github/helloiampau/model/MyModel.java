package com.github.helloiampau.model;

import com.google.gson.Gson;

/**
 * gson-example
 * Created by Pasquale Boemio <boemianrapsodi@gmail.com>
 * <p/>
 * 18 August 2014.
 */
public class MyModel {

  private String aStringValue;
  private Integer anIntegerValue;

  public String getaStringValue() {
    return aStringValue;
  }

  public void setaStringValue(String aStringValue) {
    this.aStringValue = aStringValue;
  }

  public Integer getAnIntegerValue() {
    return anIntegerValue;
  }

  public void setAnIntegerValue(Integer anIntegerValue) {
    this.anIntegerValue = anIntegerValue;
  }

  public String toJson() {
    Gson gson = new Gson();

    return gson.toJson(this);
  }

  public static MyModel fromJson(String json) {
    Gson gson = new Gson();

    return gson.fromJson(json, MyModel.class);
  }
}
