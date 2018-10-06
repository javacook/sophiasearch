package de.kotlincook.sophiasearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;

@SuppressWarnings("restriction")
public class JavaApp {
	
	public static void main(String[] args) {
	    Sequence<String> seq = FileUtils.INSTANCE.loadResourceLines("/streets.txt", Charsets.UTF_8);
	    List<String> lines = new ArrayList<String>();
	    for(Iterator<String> iter = seq.iterator(); iter.hasNext();) {
	    	lines.add(iter.next());
	    }
	    App.completer = new SophiaSearch(lines);
		Application.launch(App.class, args);
	}
}