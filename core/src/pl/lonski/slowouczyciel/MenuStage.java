package pl.lonski.slowouczyciel;

class MenuStage extends ButtonsStage {

	private final Slowouczyciel slowouczyciel;

	MenuStage(final Slowouczyciel slowouczyciel) {
		super();
		this.slowouczyciel = slowouczyciel;
		addTitle("Słowouczyciel");
		for (Config.Dataset dataset : slowouczyciel.getConfig().datasets) {
			addButton(dataset.name, loadWords(dataset));
		}
	}

	private Runnable loadWords(final Config.Dataset dataset) {
		return new Runnable() {
			@Override
			public void run() {
				MenuStage.this.slowouczyciel.learnWords(dataset);
			}
		};
	}
}
