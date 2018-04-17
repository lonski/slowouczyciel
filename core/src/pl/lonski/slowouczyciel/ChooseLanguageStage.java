package pl.lonski.slowouczyciel;

class ChooseLanguageStage extends ButtonsStage {

	private final Slowouczyciel slowouczyciel;

	ChooseLanguageStage(final Slowouczyciel slowouczyciel) {
		super();
		this.slowouczyciel = slowouczyciel;
		addTitle("Choose language");
		addButton("English", loadConfig("config-en.json"));
		addButton("Polski", loadConfig("config-pl.json"));
	}

	private Runnable loadConfig(final String configFn) {
		return new Runnable() {
			@Override
			public void run() {
				ChooseLanguageStage.this.slowouczyciel.setConfig(configFn);
				ChooseLanguageStage.this.slowouczyciel.gameMenu();
			}
		};
	}
}
