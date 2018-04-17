package pl.lonski.worducator;

class ChooseLanguageStage extends ButtonsStage {

	private final Worducator worducator;

	ChooseLanguageStage(final Worducator worducator) {
		super();
		this.worducator = worducator;
		addTitle("Choose language");
		addButton("English", loadConfig("config-en.json"));
		addButton("Polski", loadConfig("config-pl.json"));
	}

	private Runnable loadConfig(final String configFn) {
		return new Runnable() {
			@Override
			public void run() {
				ChooseLanguageStage.this.worducator.setConfig(configFn);
				ChooseLanguageStage.this.worducator.gameMenu();
			}
		};
	}
}
