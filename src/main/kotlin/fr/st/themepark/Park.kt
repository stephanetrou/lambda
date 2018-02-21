package fr.st.themepark


enum class Park(val resort : Resort , val id : String ) {

    PARC_DISNEYLAND(Resort.DISNEYLAND_PARIS, "P1"),
    PARC_WALT_DISNEY_STUDIOS(Resort.DISNEYLAND_PARIS, "P2"),
    DISNEYLAND_RESORT_CALIFORNIA_ADVENTURE(Resort.DISNEYLAND_CALIFORNIA_ADVENTURE, "336894")

}