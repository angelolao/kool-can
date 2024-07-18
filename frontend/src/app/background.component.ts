import { Component, OnInit } from '@angular/core';
import { NgParticlesModule } from "ng-particles";
import { loadFull } from "tsparticles";
import { Engine, ISourceOptions, MoveDirection, OutMode } from "tsparticles-engine";

@Component({
  selector: 'app-background',
  standalone: true,
  imports: [NgParticlesModule],
  template: `
    <ng-particles
      [id]="id"
      [options]="particlesOptions"
      [particlesInit]="particlesInit"
    ></ng-particles>
  `,
  styles: [`
    :host {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      z-index: 0;
    }
    ng-particles {
      width: 100%;
      height: 100%;
    }
  `]
})
export class BackgroundComponent implements OnInit {
  id = "tsparticles";

  particlesOptions: ISourceOptions = {
    background: {
      color: {
        value: "#000000", // Deep blue night sky
      },
    },
    fpsLimit: 60,
    interactivity: {
      events: {
        onClick: {
          enable: false, // Disable click interactions
        },
        onHover: {
          enable: false, // Disable hover interactions
        },
        resize: true,
      },
    },
    particles: {
      color: {
        value: "#ffffff",
      },
      move: {
        direction: MoveDirection.none,
        enable: true,
        outModes: {
          default: OutMode.out,
        },
        random: true,
        speed: 0.1,
        straight: false,
      },
      number: {
        density: {
          enable: true,
          area: 800,
        },
        value: 80,
      },
      opacity: {
        value: { min: 0.1, max: 1 },
        animation: {
          enable: true,
          speed: 1,
          minimumValue: 0.1,
        },
      },
      shape: {
        type: "circle",
      },
      size: {
        value: { min: 0.5, max: 3 },
      },
    },
    detectRetina: true,
  };

  constructor() {}

  ngOnInit() {}

  particlesInit = async (engine: Engine): Promise<void> => {
    console.log('Particles initialization started');
    await loadFull(engine);
    console.log('Particles initialization completed');
  };
}