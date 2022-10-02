package org.sopt.sample.week1.common

enum class MBTI {
    INTJ,
    INTP,
    INFJ,
    INFP,
    ISTJ,
    ISTP,
    ISFJ,
    ISFP,
    ENTJ,
    ENTP,
    ENFJ,
    ENFP,
    ESTJ,
    ESTP,
    ESFJ,
    ESFP,
    INVALID;

    companion object {
        fun from(target: String): MBTI =
            values().firstOrNull() { mbti ->
                mbti.toString() == target.uppercase()
            } ?: INVALID
    }
}