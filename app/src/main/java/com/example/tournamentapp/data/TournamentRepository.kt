//package com.example.tournamentapp.data
//
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//object TournamentRepository {
//    fun fetchTournamentDetails(tournamentId: Int, onResult: (Tournament?) -> Unit) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val tournament = ApiClient.apiService.getTournamentDetails(tournamentId)
//                withContext(Dispatchers.Main) {
//                    onResult(tournament)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                withContext(Dispatchers.Main) {
//                    onResult(null)
//                }
//            }
//        }
//    }
//}
