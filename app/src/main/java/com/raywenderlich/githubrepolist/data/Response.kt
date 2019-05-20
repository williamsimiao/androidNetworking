package com.raywenderlich.githubrepolist.data

data class RepoResult(val items: List<movieModel>)

enum class MovieCodingKeys(val color: String) {
    ID("id"),
    POSTER_PATH("poster_path"),
    BACKDROP_PATH("backdrop_path"),
    TITLE("title"),
    RELEASEDATE("release_date"),
    RATING("vote_average"),
    OVERVIEW("overview"),
}

data class movieModel (
        val id: Int,
        val posterPath: String,
        val backdrop: String,
        val title: String,
        val releaseDate: String,
        val rating: Double,
        val overview: String)

//        val id: Long?,
//        val name: String?,
//        val full_name: String?,
//        val owner: Owner,
//        val private: Boolean,
//        val html_url: String?,
//        val description: String?)
