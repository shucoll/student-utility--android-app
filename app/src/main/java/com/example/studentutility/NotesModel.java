package com.example.studentutility;

public class NotesModel {

    //members
    private int id;
    private String title;
    private String note;

    //constructors
    public NotesModel(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }


    public NotesModel() {
    }


    /*
    //toString to display all elements
    @Override
    public String toString() {

        return "NotesModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

     */

    //to display all the titles in the list view
    @Override
    public String toString() {

        return title;
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
