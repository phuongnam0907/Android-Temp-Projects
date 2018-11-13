/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.phuon.androidtvapptutorial;

import com.example.phuon.androidtvapptutorial.Movie;

import java.util.ArrayList;
import java.util.List;

public final class MovieList {

    public static final String MOVIE_CATEGORY[] = {
            "Category Zero",
            "Category One",
            "Category Two",
            "Category Three",
            "Category Four",
            "Category Five",
    };

    private static List<Movie> list;
    private static long count = 0;

    public static List<Movie> getList() {
        if (list == null) {
            list = setupMovies();
        }
        return list;
    }

    public static List<Movie> setupMovies() {
        list = new ArrayList<>();
        String title[] = {
                "Havana",
                "I'm Not Her",
                "The River",
                "Save Me",
                "Road So Far"
        };

        String description [] = {"Camila Cabello - Havana (Audio) ft. Young Thug",
                "Clara Mae - I'm Not Her (Official Video)",
                "Bản EDM đang gây sốt 2018 | Axel Johansson - The River [Lyrics Video] | ➞ Welcome to Vietnam",
                "DEAMN - Save Me (Vietsub Lyric)",
                "Tonyz - Road So Far (Inspired By Alan Walker) [NCN Release] "};
        String studio[] = {
                "3 phút, 27 giây", "4 phút, 6 giây", "3 phút, 55 giây", "4 phút, 13 giây", "3 phút, 31 giây"
        };
        String videoUrl[] = {
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4"
        };
        String bgImageUrl[] = {
                "https://i.ytimg.com/vi/HCjNJDNzw8Y/hqdefault.jpg",
                "https://i.ytimg.com/vi/iUNxOzxPEVI/hqdefault.jpg",
                "https://i.ytimg.com/vi/eH2WNtL5ong/hqdefault.jpg",
                "https://i.ytimg.com/vi/NVWkIgp04pA/hqdefault.jpg",
                "https://i.ytimg.com/vi/MVMIwIJtMdU/hqdefault.jpg"
        };
        String cardImageUrl[] = {
               "https://i.ytimg.com/vi/HCjNJDNzw8Y/hqdefault.jpg",
                "https://i.ytimg.com/vi/iUNxOzxPEVI/hqdefault.jpg",
                "https://i.ytimg.com/vi/eH2WNtL5ong/hqdefault.jpg",
                "https://i.ytimg.com/vi/NVWkIgp04pA/hqdefault.jpg",
                "https://i.ytimg.com/vi/MVMIwIJtMdU/hqdefault.jpg"
        };

        for (int index = 0; index < title.length; ++index) {
            list.add(
                    buildMovieInfo(
                            title[index],
                            description[index],
                            studio[index],
                            videoUrl[index],
                            cardImageUrl[index],
                            bgImageUrl[index]));
        }

        return list;
    }

    private static Movie buildMovieInfo(
            String title,
            String description,
            String studio,
            String videoUrl,
            String cardImageUrl,
            String backgroundImageUrl) {
        Movie movie = new Movie();
        movie.setId(count++);
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setStudio(studio);
        movie.setCardImageUrl(cardImageUrl);
        movie.setBackgroundImageUrl(backgroundImageUrl);
        movie.setVideoUrl(videoUrl);
        return movie;
    }
}