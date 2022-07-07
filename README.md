# java-manga-author-lookup-tool

<!-- ABOUT THE PROJECT -->

## What is this?

This is a Manga (Japanese comics) author look up tool created with basic Java.

### Features

- Look up author name with keyword(s) of Manga title
- Admin tools
  - Login
  - Add new entry (Manga title & author pair value)
  - Edit existing entry
  - Delete entry with specified title of Manga

Developing this app helped me put my basic Java knowledge into practice and familiarize myself with the language. And it was fun!

There are several other features that could be added on top and also some parts could be improved, but overall this became a simple decent app!

### Built With

- [![Java][java]][java-url]

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

- Java needs to be installed on local machine: [Download here](https://www.oracle.com/java/technologies/downloads/)

### Installation

1. Clone the repo
   ```sh
   git clone git@github.com:smtr0602/java-manga-author-lookup-tool.git
   ```
1. Inside `src` directory, run the following to compile:
   ```sh
   javac App.java
   ```
1. Then run the following to start the app:
   ```
   java App
   ```

<!-- USAGE EXAMPLES -->

## Usage

### Look up author name

Enter keyword(s) and all the matching Manga titles with the keyword(s) will show:

```
> Enter keyword(s) of Manga to find:
```

Example ) If you were to type a keyword "on", all Manga titles with the keyword "on" will show

```
> 3 item(s) found. Select with number key
1: Demon Slayer
2: Dragon Ball
3: One Piece
```

Then press number key to select, and result will show!

```
************* Author Info *************

Author (English) : 「Koyoharu Gotouge」
Author (Japanese) : 「吾峠 呼世晴」

***************************************
```

### Admin tools

Login with username and password:
(Login is only required for the first time you open admin menu.)  
default username & password:  
`username: admin`  
`password: 1234`

```
----------------------------------------------
 Please login:
----------------------------------------------
Enter username:
```

#### Add new entry

Enter Manga title and its author name to add

```
Enter title of Manga :
(Input will be automatically capitalized)
```

#### Edit existing entry

Find Manga to update and enter new author name

```
Enter new author name (English)
Akira Toriyama

Enter new author name (Japanese)
```

#### Delete existing entry

Find Manga in the same way you look up Manga and delete it

```
> Please Confirm the following:
・・・・・・・・・・・・・・・・・・・
 Delete 「Dragon Ball」?
・・・・・・・・・・・・・・・・・・・
```

<!-- ROADMAP -->

## Roadmap

- [ ] Integrate with database

### Features that could be added

- Add "add new admin users" feature

<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

Helpful resources I would like to give credit to!

- [Img Shields](https://shields.io)
- [othneildrew/Best-README-Template](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->

[java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[java-url]: https://www.java.com/en/
