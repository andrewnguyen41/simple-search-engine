# Simple Search Engine

This is a basic search engine designed to efficiently retrieve relevant information from HTML documents. It employs several key features to enhance search accuracy and speed.

## Features

- **HTML Extraction:** Utilizes Jsoup, a Java library for parsing HTML, to extract text content from web pages.

- **Text Preprocessing:**
  - **Regex:** Employs regular expressions to clean and format the extracted text.
  - **Stop Words Removal:** Filters out common, uninformative words to focus on meaningful content.
  - **Spell Checking:** Corrects typos and misspelled words, improving search results.
  - **Stemming:** Reduces words to their root form for better search matching.

- **Search Algorithm:** Utilizes the Edit Distance algorithm to find similar strings, enhancing search robustness.

- **Caching:** Recent search results are stored in an in-memory cache, improving response times for frequently searched queries.

## Implementation Flow
![Implementation Flow](./flow.png)

## Prerequisites

Before you begin, ensure you have [Java](https://www.oracle.com/ca-en/java/technologies/downloads/) installed on your system.

## Getting Started

1. Clone the repository to your local machine.
   ```
   git clone https://github.com/yourusername/simple-search-engine.git
   ```
2. Navigate to the project directory.
   ```
   cd simple-search-engine
   ```
3. Open the project in your preferred Java development environment.

4. Explore the code and feel free to make adjustments to meet your specific requirements.

## Usage

To use the search engine, follow these steps:

1. Build and run the project in your Java development environment.

2. Input your search query through the provided interface.

3. Review the search results displayed.

## Customization

You can customize the search engine to suit your specific needs. Some potential areas for customization include:

- Adding additional text preprocessing steps or algorithms for improved search results.
- Implementing alternative search algorithms for different use cases.
- Modifying the caching mechanism to better align with your application's requirements.

## Support

If you have any questions, encounter issues, or would like to contribute, please don't hesitate to reach out. Your feedback is valuable!

Happy coding!
