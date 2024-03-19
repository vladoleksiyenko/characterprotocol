
Simple Video-Game Character Protocol (SVGCP)
This is a protocol that allows the user to create unique video game characters
with a specified health amount and a damage amount. It also allows the user to change these values
once the character is created, and also allows them to display the health amount and damage amount
whenever they need.

| Client Request               | Server Response                                 | Description                                                    |
|------------------------------|-------------------------------------------------|----------------------------------------------------------------|
| HEALTH_INCREASE/DECREASE n a | the n and health amount for character a         | Increases or reduces the amount of health by n for character a |
| DAMAGE_INCREASE/DECREASE n a | the n and damage amount for character a         | Increases or reduces the amount of damage by n for character a |
| GET_HEALTH/DAMAGE a          | the character a and amount of health and damage | Displays the current health and damage for character a         |
| QUIT                         | Quit the connection                             |                                                                |