import { KashierConfig } from "./src/KashierConfig";
import { KashierInit } from "./src/KashierInit";
import { KashierServices } from "./src/KashierServices";

const Kashier = {
  ...KashierConfig,
  ...KashierInit,
  ...KashierServices
};
export { Kashier };
