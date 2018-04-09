package pl.lonski.slowouczyciel;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class WordLoader {

	private final int total;
	private boolean isLoaded;
	private List<pl.lonski.slowouczyciel.Word> words;
	private List<FileHandle> files;

	WordLoader(String folder) {
		this.isLoaded = false;
		this.words = new ArrayList<>();
		this.files = new ArrayList<>(Arrays.asList(Gdx.files.internal(folder).list()));
		this.total = files.size();
	}

	void loadNext() {
		if (files.isEmpty()) {
			Collections.shuffle(words);
			isLoaded = true;
		} else {
			loadSingleFile(files.remove(0));
		}
	}

	private void loadSingleFile(FileHandle file) {
		String name = file.name().substring(0, file.name().indexOf('.'));
		System.out.println("Loading " + name);
		words.add(new pl.lonski.slowouczyciel.Word(file, name));
	}

	boolean isDone() {
		return isLoaded;
	}

	List<pl.lonski.slowouczyciel.Word> getWords() {
		return words;
	}

	int getTotalCount() {
		return total;
	}

	int getLoadedCount() {
		return total - files.size();
	}
}
