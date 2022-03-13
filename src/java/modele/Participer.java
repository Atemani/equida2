/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author bastu
 */
public class Participer {
    
    private Cheval unCheval;
    private Course uneCourse;
    private int place;

    public Participer() {
    }

    public Participer(Cheval unCheval, Course uneCourse, int place) {
        this.unCheval = unCheval;
        this.uneCourse = uneCourse;
        this.place = place;
    }

    public Cheval getUnCheval() {
        return unCheval;
    }

    public void setUnCheval(Cheval unCheval) {
        this.unCheval = unCheval;
    }

    public Course getUneCourse() {
        return uneCourse;
    }

    public void setUneCourse(Course uneCourse) {
        this.uneCourse = uneCourse;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
    
}
