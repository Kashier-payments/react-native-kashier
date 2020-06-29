import { KashierConfig } from "./src/KashierConfig";
import { KashierInit } from "./src/KashierInit";
import { KashierServices } from "./src/KashierServices";
import { Card } from "./src/Model/Card";

const Kashier = {
  ...KashierConfig,
  ...KashierInit,
  ...KashierServices,
  Card
};
export { Kashier };
