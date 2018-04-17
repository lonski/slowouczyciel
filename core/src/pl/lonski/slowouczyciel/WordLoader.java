package pl.lonski.slowouczyciel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import pl.lonski.slowouczyciel.Config.Dataset.WordFile;

public class WordLoader {

	private final int total;
	private boolean isLoaded;
	private final String dataDirectory;
	private List<Word> words;
	private List<WordFile> files;

	WordLoader(Config.Dataset dataset) {
		this.isLoaded = false;
		this.words = new ArrayList<>();
		this.files = new ArrayList<>(dataset.wordFiles);
		this.dataDirectory = dataset.directory;
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

	private void loadSingleFile(WordFile file) {
		FileHandle fh = Gdx.files.internal(dataDirectory + "/" + file.filename);
		words.add(new Word(fh, file.name, file.isPlural));
	}

	boolean isDone() {
		return isLoaded;
	}

	List<Word> getWords() {
		return words;
	}

	int getTotalCount() {
		return total;
	}

	int getLoadedCount() {
		return total - files.size();
	}
}
