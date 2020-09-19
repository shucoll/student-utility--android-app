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

    public NotesModel(String title) {
        //this.id = id;
        this.title = title;
        //this.note = note;
    }

    public NotesModel() {
    }

    public String displayAllTitles() {
        return title;
    }

    //toString method to display the title of the notes
    @Override
    public String toString() {
        /*
        return "NotesModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                '}';
         */
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
