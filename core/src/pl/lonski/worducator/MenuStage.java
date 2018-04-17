package pl.lonski.worducator;

class MenuStage extends pl.lonski.worducator.ButtonsStage {

	private final Worducator worducator;

	MenuStage(final Worducator worducator) {
		super();
		this.worducator = worducator;
		addTitle("Worducator");
		for (Config.Dataset dataset : worducator.getConfig().datasets) {
			addButton(dataset.name, loadWords(dataset));
		}
	}

	private Runnable loadWords(final Config.Dataset dataset) {
		return new Runnable() {
			@Override
			public void run() {
				MenuStage.this.worducator.learnWords(dataset);
			}
		};
	}
}
