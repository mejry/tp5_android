package com.example.tp5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        listView = findViewById(R.id.listView);

        // Remplir la liste d'étudiants avec des données codées en dur
        students = new ArrayList<>();
        students.add(new Student("Aziz", 15));
        students.add(new Student("youness", 18));
        students.add(new Student("youssef", 12));
        students.add(new Student("Adem", 20));

        // Créer et définir l'adaptateur pour l'AutoCompleteTextView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                getAllStudentNames());
        autoCompleteTextView.setAdapter(adapter);

        // Définir un écouteur de sélection pour l'AutoCompleteTextView
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedStudent = (String) parent.getItemAtPosition(position);
            displayStudentNotes(selectedStudent);
        });
    }

    private List<String> getAllStudentNames() {
        List<String> names = new ArrayList<>();
        for (Student student : students) {
            names.add(student.getName());
        }
        return names;
    }

    private void displayStudentNotes(String studentName) {
        List<String> notes = getStudentNotes(studentName);
        ArrayAdapter<String> notesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, notes);
        listView.setAdapter(notesAdapter);
    }

    private List<String> getStudentNotes(String studentName) {
        List<String> notes = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                notes.add("Note: " + student.getGrade());
                // Ajouter ici la logique pour les icônes représentant le niveau de réussite
            }
        }
        return notes;
    }

    // Classe représentant un étudiant avec son nom et sa note
    private static class Student {
        private String name;
        private int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public int getGrade() {
            return grade;
        }
    }
}
